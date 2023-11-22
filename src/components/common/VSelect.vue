<script setup>
import { ref } from "vue";
const params = defineProps({ selectOption: Array });
const emit = defineEmits(["onKeySelect"]);

const key = ref(params.selectOption[0].value);

const onSelect = () => {
  emit("onKeySelect", key.value);
};
</script>

<template>
  <select v-model="key" @change="onSelect">
    <option
      v-for="option in selectOption"
      :key="option.value"
      :value="option.value"
      :disabled="option.value === '' ? true : false"
    >
      {{ option.text }}
    </option>
  </select>
</template>

<style scoped></style>
