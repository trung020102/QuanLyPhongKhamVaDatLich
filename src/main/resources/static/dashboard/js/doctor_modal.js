$(document).ready(function() {
    assignDataToTable();

    $('.openDoctorModalBtn').on('click', function() {
        openModalDoctor();
    });

    function openModalDoctor() {
        var doctorModal = document.getElementById("doctorModal");
        doctorModal.style.display = "block";

        var closeDoctorBtn = document.querySelector('.doctorClose');
        closeDoctorBtn.addEventListener('click', function() {
            closeDoctorModal();
        });
    }

    function closeDoctorModal() {
        var doctorModal = document.getElementById("doctorModal");
        doctorModal.style.display = "none";
    }

    $('table').on('click', '#openDoctorEditModal', function() {
        var id = $(this).data('id');
        $('#doctor_id_edit').val(id);  // Set the doctor ID in the hidden field
        openEditModalDoctor(id);
    });

    function openEditModalDoctor(id) {
        var doctorEditModal = document.getElementById("doctorEditModal");
        doctorEditModal.style.display = "block";

        var closeEditDoctorBtn = document.querySelector('.doctorEditClose');
        closeEditDoctorBtn.addEventListener('click', function() {
            closeEditDoctorModal();
        });

        console.log("Fetching data for doctor ID:", id);  // Add this log

        // Fetch and fill the doctor data into the edit form
        $.ajax({
            type: "GET",
            url: "http://localhost:8082/api/doctors/get/" + id,
            success: function(data) {
                console.log("Data received:", data);  // Add this log

                $('#doctor_name_edit').val(data.name);
                $('#doctor_specialty_edit').val(data.specialty);
                $('#doctor_workplace_edit').val(data.workplace);
                $('#doctor_diploma_edit').val(data.diploma);
                $('#doctor_introduction_edit').val(data.introduction);
                // Assuming avatar is a URL or data URI
                $('#changeAvatar').attr('src', data.avatar);
            },
            error: function(err) {
                console.log("Error fetching doctor data:", err);  // Add this log
                alert("Error fetching doctor data: " + err.responseText);
            }
        });
    }

    function closeEditDoctorModal() {
        var doctorEditModal = document.getElementById("doctorEditModal");
        doctorEditModal.style.display = "none";
    }

    var profilePic = document.getElementById("avatar");
    var avatarBtn = document.getElementById("upload_image");
    avatarBtn.onchange = function() {
        profilePic.src = URL.createObjectURL(avatarBtn.files[0]);
    }

    var changeProfilePic = document.getElementById("changeAvatar");
    var changeAvatarBtn = document.getElementById("change_upload_image");
    changeAvatarBtn.onchange = function() {
        changeProfilePic.src = URL.createObjectURL(changeAvatarBtn.files[0]);
    }

    $('table').on('click', '#delete', function() {
        var id = $(this).closest('tr').find('td:first').text();
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8082/api/doctors/delete/" + id,
            success: function(data) {
                alert("Delete success");
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
            }
        });
    });

    $('#okBtn').on('click', function() {
        var avatar = $("#upload_image").val();
        var name = $("#doctor_name").val();
        var specialty = $("#doctor_specialty").val();
        var workplace = $("#doctor_workplace").val();
        var diploma = $("#doctor_diploma").val();
        var introduction = $("#doctor_introduction").val();

        if (!name || !specialty || !diploma || !workplace || !introduction) {
            alert("All fields are required.");
            return;
        }

        var jsonVar = {
            avatar: avatar,
            name: name,
            specialty: specialty,
            diploma: diploma,
            workplace: workplace,
            introduction: introduction
        };

        console.log(JSON.stringify(jsonVar));

        $.ajax({
            type: "POST",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/create",
            success: function(data) {
                closeDoctorModal();
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
                alert("Error: " + err.responseText);
            }
        });
    });

    $('#okEditBtn').on('click', function() {
        var id = $('#doctor_id_edit').val(); // Get the doctor ID from the hidden field
        var avatar = $("#change_upload_image").val();
        var name = $("#doctor_name_edit").val();
        var specialty = $("#doctor_specialty_edit").val();
        var workplace = $("#doctor_workplace_edit").val();
        var diploma = $("#doctor_diploma_edit").val();
        var introduction = $("#doctor_introduction_edit").val();

        var jsonVar = {
            avatar: avatar,
            name: name,
            specialty: specialty,
            diploma: diploma,
            workplace: workplace,
            introduction: introduction
        };

        console.log("Updating doctor with ID:", id);
        console.log(JSON.stringify(jsonVar));

        $.ajax({
            type: "PUT",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/put/" + id,
            success: function(data) {
                $("#change_upload_image").val("");
                $("#doctor_name_edit").val("");
                $("#doctor_specialty_edit").val("");
                $("#doctor_workplace_edit").val("");
                $("#doctor_diploma_edit").val("");
                $("#doctor_introduction_edit").val("");
                closeEditDoctorModal();
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
                alert("Error: " + err.responseText);
            }
        });
    });

    function assignDataToTable() {
        $("tbody").empty();
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/get",
            success: function(data) {
                var doctor = JSON.parse(JSON.stringify(data));
                for (var i in doctor) {
                    $("tbody").append("<tr>" +
                        "<td>" + doctor[i].id + "</td>" +
                        "<td>" + doctor[i].avatar + "</td>" +
                        "<td>" + doctor[i].name + "</td>" +
                        "<td>" + doctor[i].specialty + "</td>" +
                        "<td>" + doctor[i].diploma + "</td>" +
                        "<td>" + doctor[i].workplace + "</td>" +
                        "<td>" + doctor[i].introduction + "</td>" +
                        "<td class='button-cell'><div class='table-button-container'><button class='btn btn-primary' data-id='"
                        + doctor[i].id + "' id='openDoctorEditModal'>Edit</button>" +
                        "<button class='btn btn-danger' id='delete'>Delete</button></div></td>" +
                    "</tr>");
                }
            },
            error: function(data) {
                console.log(data);
            }
        });
    }
});
