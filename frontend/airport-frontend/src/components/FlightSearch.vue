<template>
  <div>
    <h2>Search Flights</h2>
    <form @submit.prevent="searchFlights">
      <label>
        Departure:
        <input v-model="departure" type="text" />
      </label>
      <label>
        Destination:
        <input v-model="destination" type="text" />
      </label>
      <label>
        Leaving Date:
        <input v-model="leavingDate" type="date" />
      </label>
      <label>
        Max Price:
        <input v-model.number="maxPrice" type="number" />
      </label>
      <button type="submit">Search</button>
      <button @click.prevent="resetFilters">Clear Filters</button>
    </form>

    <h3>Available Flights</h3>
    <ul>
      <li v-for="flight in flights" :key="flight.id">
        <span>{{ flight.departureLoc }} → {{ flight.destinationLoc }}</span>
        <span>Price: {{ flight.price }}</span>
        <span>Leaving: {{ flight.leavingDate.toLocaleDateString() }}</span>
        <button @click="selectFlight(flight)">Select</button>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import router from "@/router";

interface Flight {
  id: number
  departureLoc: string
  destinationLoc: string
  price: number
  leavingDate: Date
}

const flights = ref<Flight[]>([])
const departure = ref('')
const destination = ref('')
const maxPrice = ref<number | null>(null)
const leavingDate = ref('')

// Fetches flights from back-end
async function fetchFlights(query: string = '') {
  const url = `http://localhost:8080/flights${query}`
  const response = await fetch(url)
  const data = await response.json()
  // Convert leavingDate strings to Date objects
  flights.value = data.map((flight: any) => ({
    ...flight,
    leavingDate: new Date(flight.leavingDate)
  }))
}

// Called when the user submits the search form
async function searchFlights() {
  const params = new URLSearchParams()
  if (departure.value) params.append('departure', departure.value)
  if (destination.value) params.append('destination', destination.value)
  if (maxPrice.value !== null) params.append('price', String(maxPrice.value))
  if (leavingDate.value) params.append('leavingDate', leavingDate.value)

  const queryString = params.toString() ? `?${params.toString()}` : ''
  await fetchFlights(queryString)
}

// Reset filters and fetch all flights
function resetFilters() {
  departure.value = ''
  destination.value = ''
  maxPrice.value = null
  leavingDate.value = ''
  fetchFlights()
}

// By default, fetch all flights on page load
onMounted(() => {
  fetchFlights()
})

// Called when a flight is selected
function selectFlight(flight: Flight) {
  console.log('Selected flight:', flight)
  // Navigate to the seat selection page for the chosen flight
  router.push(`/seat-selection/${flight.id}`)
}
</script>

<style scoped>
form {
  margin-bottom: 1rem;
}
label {
  display: block;
  margin-bottom: 0.5rem;
}
button {
  margin-right: 0.5rem;
}
</style>
