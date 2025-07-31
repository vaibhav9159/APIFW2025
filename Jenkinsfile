pipeline {
    agent any

    stages {
        stage('Check Test Build') {
            steps {
                echo 'build the project'
            }
        }
        
          stage('Run unit test') 
        {
            steps
            {
                echo ("UTs done")
            }
        }
        
          stage('Run Integration tests') 
        {
            steps
            {
                echo ("Integration tests done")
            }
        }
        
         stage('Deploy to Dev') 
        {
            steps
            {
                echo ("dev deployment done")
            }
        }
        
         stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
        
         stage("Run regression tests"){
            steps{
                echo("Regression tests done")
            }
        }
        
            stage("Deploy to Stage env"){
            steps{
                echo("deploy to stage done")
            }
        } 
        
          stage("Run sanity tests"){
            steps{
                echo("sanity tests done")
            }
        } 
        
           stage("Deploy to Prod env"){
            steps{
                echo("deploy to prod done")
            }
        } 
        
        
        
    }
}