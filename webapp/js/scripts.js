// var addAnswer = function(){
//     console.log("call addAnswer!");
// };

$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
    e.preventDefault();// submit 이 자동으로 동작하는 것을 막는다.

    var queryString = $("form[name=answer]").serialize();

    $.ajax({
        type:'post',
        url:'/api/qna/addAnswer',
        data: queryString,
        dataType:'json',
        error:onError,
        success:onSuccess
    });
}

function onError(){
    console.log("error invoked");
}

function onSuccess(data){
    console.log(data);
}

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

