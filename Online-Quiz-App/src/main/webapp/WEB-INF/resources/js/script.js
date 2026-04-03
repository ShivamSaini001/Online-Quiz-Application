
function setOptionsToChooseCorrect(correctOptionId = "", allOptionsId = "") {
    const correctOption = document.getElementById(correctOptionId);
    const allOptions = document.querySelectorAll(`#${allOptionsId} input`);

    let countFilledInputField = 0;
    allOptions.forEach((option, index) => {
        correctOption.children[index + 1].innerHTML = option.value;
        correctOption.children[index + 1].value = option.value;

        if (option.value.trim() != "") {
            countFilledInputField++;
        }
    });

    if (countFilledInputField >= 4) {
        correctOption.disabled = false;
        correctOption.title = "";
    }
    else {
        correctOption.disabled = true;
        correctOption.title = "Please fill all options of the question.";
    }

}


// Form Validation Start

const allQuestionForms = document.querySelectorAll(".question-management-form");

allQuestionForms.forEach((questionForm) => {

    questionForm.querySelector("button[type='submit']").addEventListener("click", (e) => {
        questionForm.classList.add("was-validated");
    })

    questionForm.addEventListener("reset", (e) => {
        questionForm.classList.remove("was-validated");
    })
});


// Form Validation End