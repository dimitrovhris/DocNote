
    const checkbox = document.getElementById('checkbox');
    const secondDiv = document.getElementById('second-div');
    checkbox.addEventListener('click', function showSecondDiv(){
        if(checkbox.checked){
            secondDiv.style.display = 'block';
        } else{
            secondDiv.style.display = 'none';
        }
    });
