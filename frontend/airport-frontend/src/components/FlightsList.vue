<template>
  <div>
    <h1>Flights</h1>
    <ul>
      <li v-for="flight in flights" :key="flight.id">
        {{ flight.leavingDate }} → {{ flight.destinationLoc }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'

interface Flight {
  id: number
  leavingDate: string
  destinationLoc: string
  departureLoc: string
  price: number
}

const flights = ref<Flight[]>([])

onMounted(async () => {
  const response = await fetch('http://localhost:8080/flights')
  flights.value = await response.json()
})
</script>
