// Lấy tất cả các button mở modal bằng class

var openModalBtns = document.querySelectorAll('.openModalBtn');
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


// Lặp qua từng button và thêm sự kiện click
openModalBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModal();
    });
});

// Hàm mở modal
function openModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("myModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    modal.style.display = "block";
    // Gán sự kiện click cho nút đóng modal
    closeBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeModal();
    });

}

// Lấy thẻ button bằng ID
var okButton = document.getElementById("okBtn");

// Thêm sự kiện click cho nút OK
okButton.addEventListener("click", function() {
    // Đóng modal bằng cách thêm hoặc loại bỏ class "modal-open" hoặc sử dụng các phương thức khác để điều khiển modal
    closeModal();
});












// Lấy phần tử nút đóng modal
var closeBtn = document.querySelector('.close');


// Hàm ẩn modal
function closeModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("myModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    modal.style.display = "none";
}
/* modal send mail*/
// Lấy tất cả các button mở modal bằng class

var openModalSendBtns = document.querySelectorAll('.openModalSendBtn');

// Lặp qua từng button và thêm sự kiện click
openModalSendBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModalSend();
    });
});

function openModalSend() {
    // Lấy phần tử modal
    var modal = document.getElementById("sendModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    modal.style.display = "block";
    // Gán sự kiện click cho nút đóng modal
    closeSendBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeSendModal();
    });

}



// Lấy phần tử nút đóng modal
var closeSendBtn = document.querySelector('.send-close');


// Hàm ẩn modal
function closeSendModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("sendModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    modal.style.display = "none";

}

// Lấy thẻ button bằng ID
var cancelButton = document.getElementById("cancelBtn");

// Thêm sự kiện click cho nút OK
cancelButton.addEventListener("click", function() {
    // Đóng modal bằng cách thêm hoặc loại bỏ class "modal-open" hoặc sử dụng các phương thức khác để điều khiển modal
    closeSendModal();
});



//
// When the user clicks the button, open the modal and populate fields
// When the user clicks the button, open the modal and populate fields
document.addEventListener("DOMContentLoaded", function() {
    var btns = document.querySelectorAll(".openModalBtn"); // Corrected variable name from "btns" to "btn"

    btns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            var appointmentId = this.getAttribute("data-id");
            var appointmentRow = this.closest("tr");
            var modal = document.getElementById("myModal");

            if (appointmentRow) {
                document.getElementById("name").value = appointmentRow.querySelectorAll("td")[1].innerText;
                // Assuming the gender is in the 4th column
                document.getElementById("phone").value = appointmentRow.querySelectorAll("td")[3].innerText;
                document.getElementById("email").value = appointmentRow.querySelectorAll("td")[4].innerText;
                document.getElementById("date").value = appointmentRow.querySelectorAll("td")[5].innerText;
                document.getElementById("slotTime").value = appointmentRow.querySelectorAll("td")[6].innerText;
                document.getElementById("address").value = appointmentRow.querySelectorAll("td")[7].innerText;
                // You can similarly populate other fields
                modal.style.display = "block";
            }
        });
    });

    // Close the modal when the close button is clicked
    document.querySelector(".close").addEventListener("click", function() {
        document.getElementById("myModal").style.display = "none";
    });

    // Close the modal when the user clicks anywhere outside of it
    window.onclick = function(event) {
        var modal = document.getElementById("myModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
});

//////////////// send modal
document.addEventListener("DOMContentLoaded", function() {
    var btns = document.querySelectorAll(".openModalSendBtn"); // Corrected variable name from "btns" to "btn"

    btns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            var appointmentId = this.getAttribute("data-id");
            var appointmentRow = this.closest("tr");
            var modal = document.getElementById("sendModal");

            if (appointmentRow) {
                document.getElementById("gmail").value = appointmentRow.querySelectorAll("td")[4].innerText;

                // You can similarly populate other fields
                modal.style.display = "block";
            }
        });
    });

    // Close the modal when the close button is clicked
    document.querySelector(".close").addEventListener("click", function() {
        document.getElementById("sendModalModal").style.display = "none";
    });

    // Close the modal when the user clicks anywhere outside of it
    window.onclick = function(event) {
        var modal = document.getElementById("sendModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
});

// xóa appoinment
// Lấy tất cả các nút xóa
// Lấy tất cả các thẻ <a> có class "deleteBtn"
// Lấy tất cả các thẻ <a> có class "deleteBtn"
const deleteButtons = document.querySelectorAll('.deleteBtn');

// Lặp qua mỗi nút và thêm sự kiện click
deleteButtons.forEach(button => {
    button.addEventListener('click', function(event) {
        // Ngăn chặn hành động mặc định của liên kết
        event.preventDefault();

        // Lấy ID từ thuộc tính data-app-id
        const appId = this.getAttribute('data-app-id');

        // Gọi hàm xóa cuộc hẹn với ID đã lấy
        deleteAppointment(appId);
    });
});

// Hàm xóa cuộc hẹn
function deleteAppointment(appId) {
    if (confirm('Are you sure you want to delete this appointment?')) {
        // Thực hiện AJAX DELETE request
        fetch('/doctor/appointments/' + appId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to delete appointment');
                }
                // Xử lý phản hồi nếu cần
                // Ví dụ: cập nhật giao diện người dùng
            })
            .catch(error => {
                console.error('Error deleting appointment:', error);
                // Xử lý lỗi nếu cần
            });
    }
}

$(function() {
    // hiển thị nay hiện tại
    document.addEventListener('DOMContentLoaded', function() {
        var txtSearch = document.getElementById('txtSearch');
        var savedDate = localStorage.getItem('savedDate');
        var savedResults = JSON.parse(localStorage.getItem('savedResults'));

        if (savedDate) {
            txtSearch.value = savedDate;
        } else {
            var today = new Date();
            var day = String(today.getDate()).padStart(2, '0');
            var month = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
            var year = today.getFullYear();

            var todayDate = year + '-' + month + '-' + day;
            txtSearch.value = todayDate;
        }

        if (savedResults) {
            displayResults(savedResults);
        }

        txtSearch.addEventListener('change', function() {
            localStorage.setItem('savedDate', txtSearch.value);
        });

        document.getElementById('searchForm').addEventListener('submit', function(event) {
            event.preventDefault();
            var keyword = txtSearch.value;
            localStorage.setItem('savedDate', keyword);

            fetch(`/doctor/appointments?keyword=${keyword}`)
                .then(response => response.json())
                .then(data => {
                    localStorage.setItem('savedResults', JSON.stringify(data));
                    displayResults(data);
                });
        });
    });

    function displayResults(results) {
        var resultsContainer = document.getElementById('resultsContainer');
        resultsContainer.innerHTML = '';
        results.content.forEach(app => {
            var tr = document.createElement('tr');
            tr.innerHTML = `
                    <th scope="row">${app.slotTime}</th>
                    <td>${app.orderNumber}</td>
                    <td>${app.patient.name}</td>
                    <td>${app.patient.gender}</td>
                    <td style="font-weight: bold; color: ${getStatusColor(app.appointmentStatus.statusName)}">
                        ${app.appointmentStatus.statusName}
                    </td>
                    <td>
                        <button class="button-detail openModalBtn detailBtn" data-id="${app.id}">Detail</button>
                        <button class="button-send openModalSendBtn" data-id="${app.id}">Send The Invoice <i class="fa fa-check-circle"></i></button>
                        <button class="button-create" data-id="${app.id}"><i class="fa fa-plus-circle"></i></button>
                        <a onclick="return confirm('Are you sure you want to delete this Appointment?')" href="/doctor/appointments/delete/${app.id}">
                            <i class="fa fa-trash"></i>
                        </a>
                    </td>
                `;
            resultsContainer.appendChild(tr);
        });
    }

    function getStatusColor(statusName) {
        if (statusName === 'Scheduled') return '#F9AA33';
        if (statusName === 'Completed') return 'green';
        if (statusName === 'Canceled') return 'red';
        return 'black';
    }

})

//status
$(document).ready(function() {
    // Make sure modals open when the related element is clicked
    $('.hover-underline').on('click', function() {
        $('#appointmentStatusModal').modal('show');
        let appointmentId = $(this).data("appointment-id")
         $("#appointmentStatusForm").attr("action", '/doctor/appointments/' + appointmentId + "/status")
    });

    // Function to handle form submission
    function submitAppointmentStatusForm() {
        // Add your form submission logic here
        $('#appointmentStatusModal').modal('hide');
    }
});


//update status
function openModal(appId) {
    var form = document.getElementById('appointmentStatusForm');
    form.action = '/doctor/appointments/' + appId + '/status';
    // Open the modal (you might need to add the logic to actually display the modal)
}




function submitAppointmentStatusForm() {
    var form = document.getElementById('appointmentStatusForm');

    form.submit();
}

// Event listener to update form action with appointmentId
// $('#statusModal').on('show.bs.modal', function (event) {
//     var button = $(event.relatedTarget); // Button that triggered the modal
//     var appointmentId = button.data('appointment-id'); // Extract info from data-* attributes
//
//     var form = $('#appointmentStatusForm');
//     var action = '/doctor/appointments/' + appointmentId + '/status';
//     form.attr('action', action);
// });

$()