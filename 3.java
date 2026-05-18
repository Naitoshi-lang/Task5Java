const salaries = {
    Анна: 50000,
    Иван: 60000,
    Мария: 75000,
    Пётр: 48000,
    Ольга: 82000
};

// Способ 1: через Object.values() и reduce
function sumSalaries(salaries) {
    const values = Object.values(salaries);
    return values.reduce((sum, salary) => sum + salary, 0);
}

console.log(sumSalaries(salaries)); // 315000

// Способ 2: через for...in
function sumSalariesLoop(salaries) {
    let sum = 0;
    for (let name in salaries) {
        sum += salaries[name];
    }
    return sum;
}

console.log(sumSalariesLoop(salaries)); // 315000

// Обработка пустого объекта
const emptySalaries = {};
console.log(sumSalaries(emptySalaries)); // 0
