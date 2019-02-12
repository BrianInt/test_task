export class TestData {
  date: string;
  description: string;
  category: string;
  amount: number;


  constructor(date: string = '', description: string, category: string, amount: number) {
    this.date = date;
    this.description = description;
    this.category = category;
    this.amount = amount;
  }
}
