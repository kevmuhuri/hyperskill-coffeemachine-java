import java.util.Scanner;

enum MachineState {
	READY, SELECT, BUY, FILL, TAKE, REMAINING, EXIT
}

public class CoffeeMachine {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Coffee cafeteria = new Coffee(400, 540, 120, 9, 550);
		while (cafeteria.getState() != MachineState.EXIT) {
			String action = scan.next();
			cafeteria.processOrder(action);
		}
	}
}

class Coffee {
	private int water;
	private int milk;
	private int coffee;
	private int cups;
	private int money;
	private MachineState currentState;

	Coffee(int water, int milk, int coffee, int cups, int money) {
		this.water = water;
		this.milk = milk;
		this.coffee = coffee;
		this.cups = cups;
		this.money = money;
		this.setReady();
	}

	void processOrder(String action) {
		if (this.currentState == MachineState.READY) {
			this.currentState = MachineState.valueOf(action.toUpperCase());
		}
		switch (this.currentState) {
			case BUY:
				System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
				this.currentState = MachineState.SELECT;
				break;
			case SELECT:
				switch (action) {
					case "1":
						this.espresso();
						break;
					case "2":
						this.latte();
						break;
					case "3":
						this.cappuccino();
						break;
					case "back":
						break;
					default:
						System.out.println("Error: Invalid option!!!");
						break;
				}
				this.setReady();
				break;
			case FILL:
				this.fill();
				this.setReady();
				break;
			case TAKE:
				this.take();
				this.setReady();
				break;
			case REMAINING:
				this.printState();
				this.setReady();
				break;
			case EXIT:
				this.currentState = MachineState.EXIT;
				break;
			default:
				System.out.println("Error: Invalid action!!!");
				this.setReady();
				break;
		}
	}

	MachineState getState() {
		return currentState;
	}

	private void setReady() {
		this.currentState = MachineState.READY;
		System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
	}

	private void printState() {
		System.out.println("\nThe coffee machine has:");
		System.out.println(water + " of water");
		System.out.println(milk + " of milk");
		System.out.println(coffee + " of coffee beans");
		System.out.println(cups + " of disposable cups");
		System.out.println(money + " of money");
	}

	private void espresso() {
		if (water >= 250) {
			if (coffee >= 16) {
				if (cups >= 1) {
					System.out.println("I have enough resources, making you a coffee!");
					water -= 250;
					coffee -= 16;
					--cups;
					money += 4;
				} else {
					System.out.println("Sorry, not enough cups!");
				}
			} else {
				System.out.println("Sorry, not enough coffee!");
			}
		} else {
			System.out.println("Sorry, not enough water!");
		}
	}

	private void latte() {
		if (water >= 350) {
			if (milk >= 75) {
				if (coffee >= 20) {
					if (cups >= 1) {
						System.out.println("I have enough resources, making you a coffee!");
						water -= 350;
						milk -= 75;
						coffee -= 20;
						--cups;
						money += 7;
					} else {
						System.out.println("Sorry, not enough cups!");
					}
				} else {
					System.out.println("Sorry, not enough coffee!");
				}
			} else {
				System.out.println("Sorry, not enough milk!");
			}
		} else {
			System.out.println("Sorry, not enough water!");
		}
	}

	private void cappuccino() {
		if (water >= 200) {
			if (milk >= 100) {
				if (coffee >= 12) {
					if (cups >= 1) {
						System.out.println("I have enough resources, making you a coffee!");
						water -= 200;
						milk -= 100;
						coffee -= 12;
						--cups;
						money += 6;
					} else {
						System.out.println("Sorry, not enough cups!");
					}
				} else {
					System.out.println("Sorry, not enough coffee!");
				}
			} else {
				System.out.println("Sorry, not enough milk!");
			}
		} else {
			System.out.println("Sorry, not enough water!");
		}
	}

	private void fill() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Write how many ml of water do you want to add:");
		water += scan.nextInt();
		System.out.println("Write how many ml of milk do you want to add:");
		milk += scan.nextInt();
		System.out.println("Write how many grams of coffee beans do you want to add:");
		coffee += scan.nextInt();
		System.out.println("Write how many disposable cups of coffee do you want to add:");
		cups += scan.nextInt();
	}

	private void take() {
		System.out.println("I gave you $" + money);
		money = 0;
	}
}
