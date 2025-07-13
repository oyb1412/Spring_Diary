function buttonOnEnable(btn, on)
{
    btn.prop('disabled', on);
}

function sendAjaxJson(btn, type, url,  data, successFunction)
{
    buttonOnEnable(btn, true);

    $.ajax({
        type : type,
        url : url,
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(data)
    }).done(function(result){
            alert(result.message);

            if(result.success)
            {
                successFunction(result);
            }
        }).fail(function(error){
            alert(`서버 오류 발생 : ${error}`);
        }).always(function(){
            buttonOnEnable(btn, false);
        })
}

function sendAjaxQuery(btn, type, url, successFunction)
{
    buttonOnEnable(btn, true);

    $.ajax({
        type : type,
        url : url,
    }).done(function(result){
            alert(result.message);

            if(result.success)
            {
                successFunction(result);
            }
        }).fail(function(error){
            alert(`서버 오류 발생 : ${error}`);
        }).always(function(){
            buttonOnEnable(btn, false);
        })
}

function sendAjaxFormdata(btn, type, url, formData, successFunction)
{
    buttonOnEnable(btn, true);

    $.ajax({
            type : type,
            url : url,
            data : formData,
            contentType : false,
            processData : false,
        }).done(function(result){
            alert(result.message);

            if(result.success)
            {
                successFunction(result);
            }
        }).fail(function(error){
            alert(`서버 오류 발생 : ${error}`);
        }).always(function(){
            btn.prop('disabled', false);
        });
}

