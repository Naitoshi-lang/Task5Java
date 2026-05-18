const evenNumbers = {
    from: 1,
    to: 10,
    
    [Symbol.iterator]() {
        let current = this.from;
        const end = this.to;
        
        return {
            next() {
                // Находим следующее чётное число
                while (current <= end && current % 2 !== 0) {
                    current++;
                }
                
                if (current <= end) {
                    const value = current;
                    current++;
                    return { value: value, done: false };
                } else {
                    return { done: true };
                }
            }
        };
    }
};

// Использование
for (let num of evenNumbers) {
    console.log(num); // 2, 4, 6, 8, 10
}
