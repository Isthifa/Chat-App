# 💬 Real-Time Chat App with WebSocket & Kafka

A real-time chat application built with **Spring Boot**, **WebSocket**, and **Apache Kafka** for scalable message distribution.

💬 Real-Time Chat Application
A scalable, real-time chat application built with Spring Boot, WebSocket, and Apache Kafka for efficient message distribution.
#### src/main/resources/
### ├── application.properties # Configuration file
### └── templates/index.html # Chat UI

🚀 Getting Started
Prerequisites

Java: 17 or higher
Maven: 3.6 or higher
Apache Kafka: Latest version with Zookeeper
Web Browser: For testing the chat interface

Setup Instructions

Clone the Repository
git clone https://github.com/yourusername/chat-app.git
cd chat-app


Start Kafka and Zookeeper
# Start Zookeeper
> bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka (new terminal)
> bin/kafka-server-start.sh config/server.properties


Create Kafka Topic
> bin/kafka-topics.sh --create --topic chat-messages --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1


Run the Application
mvn clean install
mvn spring-boot:run

Access the app at http://localhost:8080.


🧪 Testing Multi-User Chat

Open multiple browser tabs/windows.
Go to http://localhost:8080 in each.
Enter different usernames but the same room name.
Start chatting — messages sync instantly across tabs.
<img width="1920" height="1040" alt="image" src="https://github.com/user-attachments/assets/410bae8a-6666-4aed-b380-a52e97702065" />


🛠️ Technologies

Spring Boot: Backend framework
WebSocket: Real-time communication
Apache Kafka: Message distribution
Maven: Build and dependency management
HTML/CSS/JS: Frontend interface

📝 Notes

Ensure Kafka and Zookeeper are running before starting the app.
Update application.props for non-default Kafka settings (localhost:9092).

🤝 Contributing

Fork the repository.
Create a feature branch (git checkout -b feature/your-feature).
Commit changes (git commit -m 'Add feature').
Push to the branch (git push origin feature/your-feature).
Open a pull request.

📬 Contact
Reach out to @Isthifa._ on instagram if any doubts






