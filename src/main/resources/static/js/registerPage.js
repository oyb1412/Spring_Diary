const registerPage = {
    init : function (){
        const _this = this;
        $('#btn-register').on('click', function (){
            _this.register($(this));
        })
    },
    register : function (btn){
        const username = $('#username').val();
        const name = $('#name').val();
        const password = $('#password').val();
        const confirmPassword = $('#confirmPassword').val();
        const birthDate = $('#birthDate').val();

        const data = {
            userName : username,
            name : name,
            password : password,
            confirmPassword : confirmPassword,
            birthDate : birthDate
        };

        const url = "/api/user/register";

        sendAjaxJson(btn, 'POST', url, data, function (result){
            location.href = '/';
        })
    }
}

registerPage.init();