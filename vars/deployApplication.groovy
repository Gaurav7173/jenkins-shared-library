def call() {
    echo "Deploying the application"

    sh 'docker compose down'
    sh 'docker compose up -d'

    echo "Application deployed successfully"
}
