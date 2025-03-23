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
import { ref } from 'vue'

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

async function searchFlights() {
  // Build a query string or separate endpoint
  let url = 'http://localhost:8080/api/flights'
  const params = new URLSearchParams()
  if (departure.value) params.append('departure', departure.value)
  if (destination.value) params.append('destination', destination.value)
  if (maxPrice.value !== null) params.append('price', String(maxPrice.value))
  if (leavingDate.value) params.append('leavingDate', leavingDate.value)

  const response = await fetch(`${url}?${params.toString()}`)
  flights.value = await response.json()
}

// selectFlight function is called when the user selects a flight
function selectFlight(flight: Flight) {
  console.log('Selected flight:', flight)
}
</script>
