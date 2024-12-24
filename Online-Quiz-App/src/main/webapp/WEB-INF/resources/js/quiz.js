const quizList = document.getElementById("quiz-list");
const startQuizBtn = document.getElementById("start-quiz-btn");
const quizForm = document.getElementById("quiz-form");
const questionText = document.getElementById("question-text");
const questionNumber = document.getElementById("question-number");
const totalQuestions = document.getElementById("total-questions");
const nextQuestionBtn = document.getElementById("next-question-btn");
const quizResultBtn = document.getElementById("quiz-result-btn");
const formSubmitBtn = quizForm.querySelector(`input[type="submit"]`);

const message1 = document.getElementById("message1");
const message2 = document.getElementById(`message2`);

let quizQuestion;
async function getNextQuestion(questionNumber, questionAnswer) {
    const url = `http://localhost:9000/Online-Quiz-App/user/quiz/getNextQuestion?questionNumber=${questionNumber}&&questionAnswer=${questionAnswer}`;

    await fetch(url, {
        method: "POST"
    }).then((response) => {
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        return response;
    }).then(async (data) => {
        quizQuestion = await data.json();
    })
}

// Start Quiz btn
startQuizBtn.addEventListener("click", async (e) => {

    console.log("startQuizBtn.addEventListener('click', async (e) => {");

    await getNextQuestion(Number.parseFloat(quizForm.questionNumber.value) + 1, quizForm.questionAnswer.value);
    quizList.classList.add("d-none");
    quizForm.classList.remove("d-none");

    addQuestion(quizQuestion);
})

// Submit Form 
quizForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    if (quizQuestion.questionNumber > 0 && quizQuestion.questionNumber <= quizQuestion.totalQuestions) {
        await getNextQuestion(Number.parseFloat(quizForm.questionNumber.value) + 1, quizForm.questionAnswer.value);

        if (quizQuestion.questionNumber <= 0) {
            formSubmitBtn.classList.add("d-none");
            nextQuestionBtn.classList.add("d-none");
            quizResultBtn.classList.remove("d-none");
        } else {
            formSubmitBtn.classList.add("d-none");
            nextQuestionBtn.classList.remove("d-none");
        }
        displayPreviousResult(quizQuestion.previousQuestionAnswer);
    }
})

nextQuestionBtn.addEventListener("click", async (e) => {
    if (quizQuestion.questionNumber > 0) {
        formSubmitBtn.classList.remove("d-none");
        nextQuestionBtn.classList.add("d-none");
        addQuestion(quizQuestion);
    }
})


function addQuestion(question) {

    console.log('function addQuestion(question) {')
    // Setting Question Text
    questionText.textContent = question.questionText;

    // Setting all options.
    updateInputAndLabel(quizForm.questionAnswer[0], question.optionA);
    updateInputAndLabel(quizForm.questionAnswer[1], question.optionB);
    updateInputAndLabel(quizForm.questionAnswer[2], question.optionC);
    updateInputAndLabel(quizForm.questionAnswer[3], question.optionD);

    // Setting Question number
    questionNumber.textContent = question.questionNumber;
    totalQuestions.textContent = question.totalQuestions;

    quizForm.questionNumber.value = question.questionNumber;

    message1.innerHTML = "";
    message2.innerHTML = "";
}

function updateInputAndLabel(inputElement, newValue) {

    // Update the value of the input
    inputElement.value = newValue;
    inputElement.checked = false;
    inputElement.disabled = false;

    // Find the label element using the `for` attribute
    const labelElement = document.querySelector(`label[for="${inputElement.id}"]`);

    // Update the text of the label
    labelElement.textContent = newValue;

    labelElement.classList.remove("btn-outline-success");
    labelElement.classList.remove("btn-outline-danger");

    if (!labelElement.classList.contains("btn-outline-primary"))
        labelElement.classList.add("btn-outline-primary");
}

function displayPreviousResult(previousQuestionAnswer) {
    let msg = "";
    let msg2 = "";

    let isNotAttemptQuestion = true;

    quizForm.questionAnswer.forEach((input) => {
        if (input.checked && (input.value !== previousQuestionAnswer)) {
            // wrong input
            msg = "Oops, wrong answer!";
            msg2 = `Correct Answer is <b>${previousQuestionAnswer}</b>`;
            message1.classList = "text-danger fw-bold m-0 p-0";
            message2.classList = "text-primary m-0 p-0";

            let inputLabel = document.querySelector(`label[for="${input.id}"]`)
            inputLabel.classList.toggle("btn-outline-primary");
            inputLabel.classList.toggle("btn-outline-danger");

            isNotAttemptQuestion = false;
        }
        else if (input.checked && (input.value === previousQuestionAnswer)) {
            let inputLabel = document.querySelector(`label[for="${input.id}"]`)
            inputLabel.classList.remove("btn-outline-primary");
            inputLabel.classList.add("btn-outline-success");

            msg = "You are right!";
            msg2 = `Correct Answer is <b>${previousQuestionAnswer}</b>`;
            message1.classList = "text-success fw-bold m-0 p-0";
            message2.classList = "text-primary m-0 p-0";

            isNotAttemptQuestion = false;
        }
        message1.innerHTML = msg;
        message2.innerHTML = msg2;
    });

    quizForm.questionAnswer.forEach(inp => {
        if (inp.value === previousQuestionAnswer) {
            inp.checked = true;
            let inpLabel = document.querySelector(`label[for="${inp.id}"]`)
            inpLabel.classList.toggle("btn-outline-primary");
            inpLabel.classList.toggle("btn-outline-success");

            if (isNotAttemptQuestion) {
                msg = "You are not attempt this question!";
                msg2 = `Correct Answer is <b>${previousQuestionAnswer}</b>`;
                message1.classList = "text-secondary fw-bold m-0 p-0";
                message2.classList = "text-primary m-0 p-0";
                message1.innerHTML = msg;
                message2.innerHTML = msg2;
            }
        }

        // disabled input
        inp.disabled = true;
    });


}