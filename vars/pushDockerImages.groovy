def call(String dockerhubCredentialId) {
    echo "Pushing images to Docker Hub"

    withCredentials([
        usernamePassword(
            credentialsId: "${dockerhubCredentialId}",
            passwordVariable: 'dockerhubpass',
            usernameVariable: 'dockerhubuser'
        )
    ]) {
        sh '''
            echo "$dockerhubpass" | docker login -u "$dockerhubuser" --password-stdin

            docker tag django_app:latest "$dockerhubuser/django-app:latest"
            docker push "$dockerhubuser/django-app:latest"

            docker tag nginx:latest "$dockerhubuser/notes-nginx:latest"
            docker push "$dockerhubuser/notes-nginx:latest"

            docker logout
        '''
    }

    echo "Images pushed successfully to Docker Hub"
}
