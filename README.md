**Rock Paper Scissors Plus** is a Java console application featuring three versions of the classic game: the traditional Classic with 3 gestures, the extended RPS-9 with 9, and the popular Rock Paper Scissors Lizard Spock with 5.

It uses a modular architecture based on the **Service Provider Interface (SPI)**, allowing easy addition of new variants while keeping a consistent terminal interface and gameplay.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

## 🕹️ How to Play

1. Select a game variant.
2. Choose your gesture from the list displayed.
3. The computer will pick its gesture randomly.
4. The result is displayed, showing the winner.

Available games include:

### ✂️ Rock Paper Scissors (Classic)

| Gesture      | Wins against | Loses against |
| ------------ | ------------ | ------------- |
| **Rock**     | Scissors     | Paper         |
| **Paper**    | Rock         | Scissors      |
| **Scissors** | Paper        | Rock          |

### 🔫 Rock Paper Scissors Nine (RPS-9)

| Gesture      | Wins against                   | Loses against                   |
| ------------ | ------------------------------ | ------------------------------- |
| **Rock**     | Fire, Scissors, Human, Sponge  | Paper, Air, Water, Gun          |
| **Paper**    | Rock, Air, Water, Gun          | Scissors, Fire, Human, Sponge   |
| **Scissors** | Paper, Human, Sponge, Air      | Rock, Fire, Water, Gun          |
| **Fire**     | Scissors, Paper, Human, Sponge | Rock, Air, Water, Gun           |
| **Human**    | Sponge, Paper, Air, Water      | Rock, Scissors, Fire, Gun       |
| **Sponge**   | Paper, Air, Water, Gun         | Rock, Scissors, Fire, Human     |
| **Air**      | Fire, Rock, Water, Gun         | Paper, Scissors, Human, Sponge  |
| **Water**    | Rock, Fire, Scissors, Gun      | Paper, Human, Sponge, Air       |
| **Gun**      | Rock, Scissors, Human          | Paper, Sponge, Air, Water, Fire |

### 🖖 Rock Paper Scissors Lizard Spock

| Gesture      | Wins against     | Loses against    |
| ------------ | ---------------- | ---------------- |
| **Rock**     | Scissors, Lizard | Paper, Spock     |
| **Paper**    | Rock, Spock      | Scissors, Lizard |
| **Scissors** | Paper, Lizard    | Rock, Spock      |
| **Lizard**   | Paper, Spock     | Rock, Scissors   |
| **Spock**    | Rock, Scissors   | Paper, Lizard    |

## 🛠️ Installation and Execution

This project was built with **Java 21** and **Maven 3.9** for dependency management. From the root directory, compile and run:

```bash
mvn clean package
java -cp target/classes io.github.davidsantana06.App
```

## 🧪 Test Coverage

Unit tests validate the main functionalities implemented in the service layer. To run them, use:

```bash
mvn test
```

## 🤝 Donation

If you like this project and want to support it financially, you can contribute via **PayPal** or **Pix** — _aos meus chegados do Brasil_ — by clicking one of the options below:

[![PayPal](https://img.shields.io/badge/PayPal-Donate-1040C1?labelColor=121661&style=for-the-badge&logo=paypal&link=https://www.paypal.com/donate/?hosted_button_id=2P9HPGUP7Z43S)](https://www.paypal.com/donate/?hosted_button_id=2P9HPGUP7Z43S)
[![Pix](https://img.shields.io/badge/Pix-Donate-FBB88A?labelColor=F26722&style=for-the-badge&logo=pix&logoColor=ffffff&link=https://tipa.ai/davidsantana06)](https://tipa.ai/davidsantana06)

This and other projects on my profile were developed independently. Any support to keep them going is greatly appreciated!

## 📚 References

- SENIOR JAVA TECHNICAL INTERVIEW R\$10K CLT BR COMPANY. YouTube, available at: https://youtube.com/watch?v=r_SRTbbtlD4.
- RPS-9. UMOP, available at: https://umop.com/rps9.htm.
- Rock, Paper, Scissors, Lizard, Spock. The Big Bang Theory Wiki, available at: https://bigbangtheory.fandom.com/wiki/Rock,_Paper,_Scissors,_Lizard,_Spock.

## ⚖️ License

This project uses the **MIT License**, allowing you to use and modify the code freely. The only requirement is to give proper credit, recognizing the effort and time invested.
