// 4.1
const order = {
    товары: ["ноутбук", "мышь", "клавиатура"],
    количество: 3,
    цена: 15000,
    датаЗаказа: new Date()
};

// Сериализация в JSON
const jsonString = JSON.stringify(order);
console.log(jsonString);
// {"товары":["ноутбук","мышь","клавиатура"],"количество":3,"цена":15000,"датаЗаказа":"2024-01-15T10:30:00.000Z"}

// Восстановление из JSON
const restoredOrder = JSON.parse(jsonString);
console.log(restoredOrder);
// NOTE: restoredOrder.датаЗаказа — строка, а не Date!

// 4.2 
const jsonString2 = JSON.stringify(order);
const restoredOrder2 = JSON.parse(jsonString2, (key, value) => {
    if (key === "датаЗаказа") {
        return new Date(value);
    }
    return value;
});

console.log(restoredOrder2.датаЗаказа instanceof Date); // true

// 4.3
const orderWithCustomJSON = {
    товары: ["ноутбук", "мышь", "клавиатура"],
    количество: 3,
    цена: 15000,
    датаЗаказа: new Date(),
    
    // Кастомная сериализация
    toJSON() {
        return {
            items: this.товары,
            quantity: this.количество,
            totalPrice: this.цена * this.количество,
            orderDate: this.датаЗаказа.toISOString(),
            summary: `Заказ на сумму ${this.цена * this.количество} руб.`
        };
    }
};

// Сериализация — будет использован toJSON()
const customJson = JSON.stringify(orderWithCustomJSON);
console.log(customJson);
// {"items":["ноутбук","мышь","клавиатура"],"quantity":3,"totalPrice":45000,"orderDate":"2024-01-15T10:30:00.000Z","summary":"Заказ на сумму 45000 руб."}

// Восстановление (обычный JSON.parse, так как toJSON не влияет на парсинг)
const restoredCustom = JSON.parse(customJson);
console.log(restoredCustom.summary); // Заказ на сумму 45000 руб.

// 4.4
class Order {
    constructor(товары, количество, цена, датаЗаказа) {
        this.товары = товары;
        this.количество = количество;
        this.цена = цена;
        this.датаЗаказа = датаЗаказа;
    }
    
    get totalPrice() {
        return this.цена * this.количество;
    }
    
    toJSON() {
        return {
            items: this.товары,
            quantity: this.количество,
            price: this.цена,
            total: this.totalPrice,
            date: this.датаЗаказа.toISOString(),
            type: "Order"
        };
    }
    
    static fromJSON(jsonObj) {
        return new Order(
            jsonObj.items || jsonObj.товары,
            jsonObj.quantity || jsonObj.количество,
            jsonObj.price || jsonObj.цена,
            new Date(jsonObj.date || jsonObj.датаЗаказа)
        );
    }
}

// Создание заказа
const order1 = new Order(["телефон", "чехол"], 2, 25000, new Date());

// Сериализация
const json = JSON.stringify(order1);
console.log(json);

// Восстановление
const parsed = JSON.parse(json);
const restoredOrder3 = Order.fromJSON(parsed);
console.log(restoredOrder3.totalPrice); // 50000
