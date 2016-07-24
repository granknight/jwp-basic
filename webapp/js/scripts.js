// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
$("form[name=answerDeleteForm]").on("click",  ".link-delete-article", deleteAnswer);

function deleteAnswer(e){
  e.preventDefault();
  var queryString = $("form[name=answerDeleteForm]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : dAnswerSuccess
  });
}

function dAnswerSuccess(data, status) {
  var answerId = data.answerId;
  $("#answer_" + answerId).remove();
  decreaseAnswerCount();
}

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function onSuccess(json, status){
  var questionId = json.questionId;
  var answer = json.answer;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, questionId);
  $(".qna-comment-slipp-articles").prepend(template);

  increaseAnswerCount();
  clearInputValue();
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

function increaseAnswerCount(){

  var count = $("#answerCount").text();

  $("#answerCount").text(Number(count) + 1);
}

function decreaseAnswerCount(){

  var count = $("#answerCount").text();

  $("#answerCount").text(Number(count) - 1);
}

function clearInputValue(){

  $("#contents").text("");
  $("#writer").val("");

}