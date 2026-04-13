<script setup>
import { ref, onMounted } from 'vue'

const pets = ref([])
const erro = ref('')
const carregando = ref(true)

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8080/api/pets')
    if (response.ok) {
      pets.value = await response.json()
    } else {
      erro.value = 'Erro ao buscar dados do backend'
    }
  } catch (e) {
    erro.value = 'Não foi possível conectar ao backend. Verifique o CORS!'
  } finally { carregando.value = false }
})
</script>

<template>
  <main>
    <h1>Pet Care Tracker</h1>
    
    <p v-if="carregando">🚀 Acordando o servidor, aguarde...</p>

    <div v-else>
      <p v-if="erro" style="color: red;">{{ erro }}</p>
      
      <ul v-if="pets.length > 0">
        <li v-for="pet in pets" :key="pet.id">
          {{ pet.nome }} - {{ pet.especie }}
        </li>
      </ul>
      
      <p v-else>Nenhum pet encontrado. Hora de cadastrar o Snow!</p>
    </div>
  </main>
</template>