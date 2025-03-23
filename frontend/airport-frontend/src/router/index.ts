import { createRouter, createWebHistory } from 'vue-router'
import FlightSearch from "@/components/FlightSearch.vue"
import SeatSelection from '@/components/SeatSelection.vue'

const routes = [
  { path: '/flights', component: FlightSearch },
  { path: '/seat-selection/:flightId', component: SeatSelection },
  { path: '/', redirect: '/flights' }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
