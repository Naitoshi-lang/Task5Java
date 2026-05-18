function countUniqueWords(words) {
    // Создаём Set из массива — он автоматически удаляет дубликаты
    const uniqueSet = new Set(words);
    return uniqueSet.size;
}

// Пример использования
const words = ["кот", "пёс", "кот", "птица", "пёс", "рыба"];
console.log(countUniqueWords(words)); // 4 (кот, пёс, птица, рыба)

// С учётом регистра (приводим все к нижнему регистру)
function countUniqueWordsCaseInsensitive(words) {
    const normalized = words.map(word => word.toLowerCase());
    return new Set(normalized).size;
}

const wordsWithCase = ["Кот", "кот", "КОТ", "пёс", "Пёс"];
console.log(countUniqueWordsCaseInsensitive(wordsWithCase)); // 2 (кот, пёс)
