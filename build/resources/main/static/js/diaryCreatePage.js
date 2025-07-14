const diaryCreate = {
    init : function (){
        const _this = this;
        $('#btn-create').on('click', function (){
            _this.create($(this));
        })
    },
    create : function (btn){
        const title = $('#title').val();
        const content = $('#content').val();
        const emotion = $('input[name="mood"]:checked').val();
        const dateStr = $('#dateStr').val();

        const data = {
            title : title,
            content : content,
            emotion : emotion,
            dateStr : dateStr
        };

        const url = '/api/diary/create';

        sendAjaxJson(btn, 'POST', url, data, function (result){
            location.href = '/main-page';
        })


    }
}

diaryCreate.init();