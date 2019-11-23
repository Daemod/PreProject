$(document).ready(function () {
    updateTable();
    $("#addUser").submit(function (e) {
        e.preventDefault();
        var role = {
            role: $("#role").val()
        };
        var user = {
            name: $("#name").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            roles: [role]
        };
        $.ajax("api/addUser", {
            type: "POST",
            data: $.toJSON(user),
            contentType: "application/json",
            success: function (data) {
                alert(data.name);
            },
            error: function (er) {
                console.log(er);
            }
        });
        updateTable();
        return false;
    })

});

function updateTable() {
    $("#table-body").empty();
    $.ajax({
        type: "GET",
        url: "api/getUsers",
        contentType: "application/json",
        success: function (data) {
            var users = JSON.parse(JSON.stringify(data));
            for (var i in users) {
                $("#table-body").append("" +
                    "<tr>" +
                    "<th>" + users[i].id + "</th>" +
                    "<th>" + users[i].roles[0].role + "</th>" +
                    "<th>" + users[i].name + "</th>" +
                    "<th>" + users[i].email + "</th>" +
                    "<th><button onclick='openModalEdit(" + users[i].id + ")' class='btn btn-info' data-toggle='modal' data-target='#edit'>Edit</button>" +
                    "<button onclick='deleteUser(" + users[i].i + ")' class='btn btn-outline-danger border-0'>X</button> </th></tr>");
            }
        },
        error: function (er) {
            console.log(er);
        }
    });
}

function openModalEdit(id) {
    $.ajax({
        type: "GET",
        url: "api/openModalById/" + id,
        contentType: "application/json",
        success: function (data) {
            var user = JSON.parse(JSON.stringify(data));
            $("#user-id").val(user.id);
            $("#user-email").val(user.email);
            $("#user-name").val(user.name);
            $("#user-password").val(user.password);
            $("#user-role").val(user.roles[0].role);
        },
        error: function (er) {
            console.log(er);
        }
    });
}

function sendEditInfo() {
    var role = {
        role: $("#user-role").val()
    };
    var user = {
        id: $("#user-id").val(),
        name: $("#user-name").val(),
        email: $("#user-email").val(),
        password: $("#user-password").val(),
        roles: [role]
    };

    $.ajax("api/editUser", {
        type: "POST",
        data: $.toJSON(user),
        contentType: "application/json",
        success: function (data) {
            alert(data);
        },
        error: function (er) {
            alert("Error");
        }
    });
    updateTable();
}

function deleteUser(id) {
    $.ajax({
        url: "api/deleteUserById/" + id,
        type: "POST",
        contentType: "application/json",
        success: function (data) {
            console.log(data)
        },
        error: function (er) {
            console.log(er)
        }
        }
    );

    updateTable();
}

