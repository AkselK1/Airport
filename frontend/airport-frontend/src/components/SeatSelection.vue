<template>
  <div>
    <h2>Seat Selection for Flight {{ flightId }}</h2>

    <!-- Filter Options -->
    <div class="filters">
      <label>
        Only show available seats:
        <input type="checkbox" v-model="showAvailableOnly" />
      </label>
      <label>
        More leg room:
        <input type="checkbox" v-model="showMoreRoomOnly" />
      </label>
      <label>
        Business Class Only:
        <input type="checkbox" v-model="showBusinessOnly" />
      </label>
      <label>
        Window Seats Only:
        <input type="checkbox" v-model="showWindowOnly" />
      </label>
    </div>

    <!-- Table for seat layout -->
    <table class="seat-table">
      <tbody>
      <!-- v-for over rows 1..30 -->
      <tr v-for="row in 30" :key="row">
        <!-- v-for over columns, including walkway -->
        <template v-for="col in getColumns(row)" :key="col">
          <!-- If 'W' we insert a walkway cell -->
          <td v-if="col === 'W'" class="walkway"></td>
          <!-- Otherwise it's a seat cell -->
          <td
            v-else
            :class="seatCellClass(row, col)"
            @click="onSeatClick(row, col)"
          >
            {{ row }}{{ col }}
          </td>
        </template>
      </tr>
      </tbody>
    </table>

    <!-- Show selected seat details -->
    <div v-if="selectedSeat" class="selection-info">
      <h3>You selected seat {{ selectedSeat.seatNumber }}</h3>
      <p>
        Availability: {{ selectedSeat.availability ? 'Available' : 'Taken' }}<br>
        More Room: {{ selectedSeat.moreRoom }}<br>
        Business Class: {{ selectedSeat.businessClass }}<br>
        Window Seat: {{ selectedSeat.windowSeat }}
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'

interface Seat {
  id: number
  seatNumber: string
  availability: boolean
  moreRoom: boolean
  businessClass: boolean
  windowSeat: boolean
}


// 1) Get flightId from route param

const route = useRoute()
const flightId = Number(route.params.flightId)


// Store all seats for this flight
const allSeats = ref<Seat[]>([])

// Filters
const showAvailableOnly = ref(false)
const showMoreRoomOnly = ref(false)
const showBusinessOnly = ref(false)
const showWindowOnly = ref(false)

// Selected seat (when user clicks)
const selectedSeat = ref<Seat | null>(null)

// Fetch seats from your back-end
onMounted(async () => {
  const response = await fetch(`http://localhost:8080/api/flights/${flightId}/seats`)
  const data = await response.json()
  allSeats.value = data
})

/**
 * 6) Build a quick lookup map from "1A" -> Seat object
 *    so we can easily find seats by row/col in the template
 */
const seatMap = computed(() => {
  const map: Record<string, Seat> = {}
  for (const seat of allSeats.value) {
    // seat.seatNumber is something like "1A", "7F", etc.
    map[seat.seatNumber] = seat
  }
  return map
})

/**
 * 7) getColumns(row) returns seat columns plus 'W' for walkway
 *    For rows 1..6, seats are A,B,C,D
 *    For rows >=7, seats are A,B,C,D,E,F
 *    Insert 'W' (walkway) between C and D
 */
function getColumns(row: number): Array<string> {
  if (row <= 6) {
    // 4 seats + walkway
    return ['A', 'B', 'C', 'W', 'D']
  } else {
    // 6 seats + walkway
    return ['A', 'B', 'C', 'W', 'D', 'E', 'F']
  }
}

// seatCellClass applies filters & highlights
function seatCellClass(row: number, col: string) {
  const seat = seatMap.value[`${row}${col}`]
  if (!seat) {
    return 'empty-seat' // If no seat data found, mark as empty
  }

  // Apply filter logic: if seat doesn't pass filter, hide or dim
  if (!passesFilter(seat)) {
    return 'filtered-out'
  }

  // If seat is taken
  if (!seat.availability) {
    return 'seat taken'
  }

  // If seat is selected
  if (selectedSeat.value && selectedSeat.value.id === seat.id) {
    return 'seat selected'
  }

  // Otherwise seat is normal
  return 'seat'
}

// onSeatClick - handle user clicks
function onSeatClick(row: number, col: string) {
  const seat = seatMap.value[`${row}${col}`]
  if (!seat) {
    return
  }

  // Check filters
  if (!passesFilter(seat)) {
    alert('This seat is filtered out or does not meet the criteria!')
    return
  }

  // If seat is not available
  if (!seat.availability) {
    alert('This seat is taken!')
    return
  }

  // Single-seat selection
  selectedSeat.value = seat
}

// passesFilter - checks all boolean filters
function passesFilter(seat: Seat) {
  if (showAvailableOnly.value && !seat.availability) return false
  if (showMoreRoomOnly.value && !seat.moreRoom) return false
  if (showBusinessOnly.value && !seat.businessClass) return false
  if (showWindowOnly.value && !seat.windowSeat) return false
  return true
}
</script>

<style scoped>
/* Container styling for filters (optional) */
.filters {
  margin-bottom: 1rem;
}

/* Basic table styling */
.seat-table {
  border-collapse: collapse;
}

/* We put an empty walkway cell in the middle */
.walkway {
  width: 1rem;
  background-color: transparent;
}

/* Normal seat cell styling */
.seat {
  width: 3rem;
  height: 3rem;
  background-color: lightgreen;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  border: 1px solid #ccc;
}

/* Seat is taken */
.taken {
  background-color: lightgray;
  cursor: not-allowed;
}

/* Seat is selected */
.selected {
  background-color: orange;
}

/* If seat doesn't pass the filters, you could hide it or dim it */
.filtered-out {
  background-color: #f5f5f5;
  color: #aaa;
}

/* If there's no seat data for row+col, you can style it differently */
.empty-seat {
  background-color: white;
  cursor: default;
}

/* Info about the selected seat */
.selection-info {
  margin-top: 1rem;
  font-weight: bold;
}
</style>
