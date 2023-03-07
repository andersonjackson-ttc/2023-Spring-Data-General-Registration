function tdclick(description) {
    console.log(description);
    alert(description);
   
};

function showElect() {
   let electDiv = document.getElementById("electivesToggle");
    electDiv.classList.toggle("toggleElectives");
    
};

function coreInfoClick(courseName) {
    document.querySelector('.information').classList.toggle("show");
    document.querySelector('.information').innerHTML = `<strong> ${courseName}</strong> <br> 
        This class name is ${courseName} <br> and a description for a ${courseName} goes here<br>
        ....PreReqExAmp101 min grade: C <br>
        ....PreReqExAmp102 min grade: C `; 
}

function electShowClick(){
    document.querySelector('.electiveChoices').classList.toggle("show");
}