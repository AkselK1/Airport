<template>
  <div>
    <h2>Seat Selection for Flight {{ flightId }}</h2>

    <div>
      <label>
        Only show available seats:
        <input type="checkbox" v-model="showAvailableOnly" />
      </label>
      <label>
        More leg room:
        <input type="checkbox" v-model="showMoreRoomOnly" />
      </label>
    </div>

    <ul>
      <li v-for="seat in filteredSeats" :key="seat.id">
        <span>{{ seat.seatNumber }} ({{ seat.businessClass ? 'Business' : 'Economy' }})</span>
        <span v-if="seat.availability">Available</span>
        <span v-else>Taken</span>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'

interface Seat {
  id: number
  seatNumber: string
  availability: boolean
  moreRoom: boolean
  businessClass: boolean
  // ...
}

const route = useRoute()
const flightId = Number(route.params.flightId)

const allSeats = ref<Seat[]>([])

const showAvailableOnly = ref(false)
const showMoreRoomOnly = ref(false)

// Example: fetch seats for the flight
onMounted(async () => {
  const response = await fetch(`http://localhost:8080/api/flights/${flightId}/seats`)
  allSeats.value = await response.json()
})

const filteredSeats = computed(() => {
  return allSeats.value.filter(seat => {
    if (showAvailableOnly.value && !seat.availability) return false
    if (showMoreRoomOnly.value && !seat.moreRoom) return false
    return true
  })
})
</script>
