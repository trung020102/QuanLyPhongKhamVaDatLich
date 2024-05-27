const MedicalServiceList = (function () {
    const module = {
        findAllServiceUrl: '/api/medical-service',
        serviceListTableSelector: $('#medical-service-table'),
        modalEditService: $('#serviceEditModal'),
        modalDeleteService: $('#serviceDeleteModal')

    };

    module.init = () => {
        renderMedicalServiceList()
        openEditServiceModalButton();
        handleEditServiceButton();
        openDeleteServiceModalButton();
    }

    const openEditServiceModalButton = () => {
        module.serviceListTableSelector.on('click', '.open-edit-service-modal-btn', function () {
            const rowData = module.serviceListTableSelector.DataTable().row($(this).closest('tr')).data();
            $('#service-id').val(rowData.id);
            renderServiceDetail(rowData);
            module.modalEditService.modal('show');
        })
    }

    const openDeleteServiceModalButton = () => {
        module.serviceListTableSelector.on('click', '.open-delete-service-modal-btn', function () {
            const rowData = module.serviceListTableSelector.DataTable().row($(this).closest('tr')).data();
            $('#service-id').val(rowData.id);
            module.modalDeleteService.modal('show');
            module.modalDeleteService.find('.modal-title').text(`Xác nhận xóa dịch vụ ${rowData.serviceName}`);
        })
    }

    const deleteService = (serviceId, confirmDocumentCode) => {
        $.ajax({
            type: 'DELETE',
            data: {
                confirmCode: confirmDocumentCode
            },
            url: module.deleteDocumentUrl.replace('{id}', documentCode),
        })
            .done(() => {
                location.reload();
            })
            .fail((jqXHR) => {
                App.handleResponseMessageByStatusCode(jqXHR);
            })
    }
    const renderServiceDetail = (data) => {
        $('#service_edit_name').val(data.serviceName);
        $('#service_edit_price').val(data.price);
        $('#service_edit_desciption').val(data.description);
    }
    // const renderDeleteServiceDetail = (data) => {
    //     $('#service_delete_name').val(data.serviceName);
    // }
    const handleEditServiceButton = () => {
        $('#okeEditServiceBtn').on('click', function () {

            const serviceParam = {
                serviceName: $('#service_edit_name').val().trim(),
                price: $('#service_edit_price').val().trim(),
                description: $('#service_edit_desciption').val().trim(),
            }
            const serviceId = $('#service-id').val();
            $.ajax({
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                type: 'PUT',
                url: MedicalServiceCreate.createServiceUrl + '/' + serviceId,
                data: JSON.stringify(serviceParam),
            })
                .done(() => {
                    alert("Sửa dịch vụ thành công")
                    window.location.href = "/admin/service";
                })
                .fail((jqXhr) => {
                    console.log(jqXhr)
                })
        })
    }

    const renderMedicalServiceList = () => {
        const serviceListDatatable = module.serviceListTableSelector.DataTable({
            ajax: {
                url: module.findAllServiceUrl,
                dataSrc: ''
            },
            columns: [
                {data: null},
                {data: 'serviceName'},
                {data: 'price'},
                {data: 'description'},
                {data: null},
            ],
            bJQueryUI: true,
            processing: true,
            destroy: true,
            paging: true,
            searching: false,
            lengthChange: false,
            info: false,
            ordering: false,
            pageLength: 10, // Set the default records displaying on each page
            pagingType: 'simple_numbers',
            columnDefs: [
                {
                    targets: [0, 2],
                    className: "text-center"
                },
                {
                    targets: -1,
                    className: "text-center",
                    render: () =>
                        `<button class="open-edit-service-modal-btn btn btn-sm btn-outline-primary border-0">
                            <i class="fa fa-edit"></i>
                        </button>
                        <button class="open-delete-service-modal-btn btn btn-sm btn-outline-danger border-0"
                                    data-toggle="modal" data-target="#delete-document-confirm-modal">
                                <i class="fa fa-trash-alt"></i>
                        </button>

                },
            ],
        });
        serviceListDatatable.on('draw.dt', function () {
            const PageInfo = serviceListDatatable.page.info();
            serviceListDatatable.column(0, {page: 'current'}).nodes().each(function (cell, i) {
                cell.innerHTML = i + 1 + PageInfo.start;
            });
        });
    }
    return module;
})();

const MedicalServiceCreate = (function () {
    const module = {
        createServiceUrl: '/api/medical-service',
        modalCreateService: $('#serviceCreateModal'),
        serviceNameSelector: $('#service_name'),
        servicePriceSelector: $('#service_price'),
        serviceDescriptionSelector: $('#service_description'),
        createBtnSelector: $('#okServiceBtn'),
    }
    module.init = () => {
        handleCreateDoctor();
    }


    const handleCreateDoctor = () => {
        module.createBtnSelector.on('click', function () {
            const service = {
                serviceName: module.serviceNameSelector.val().trim(),
                price: module.servicePriceSelector.val(),
                description: module.serviceDescriptionSelector.val().trim(),
            };

            $.ajax({
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                type: 'POST',
                url: module.createServiceUrl,
                data: JSON.stringify(service),
            })
                .done(() => {
                    alert("Tạo bác sĩ thành công")
                    window.location.href = "/admin/service";
                })
                .fail((jqXhr) => {
                    console.log(jqXhr)
                })
        })
    }
    return module;
})();


$(function () {
    MedicalServiceList.init();
    MedicalServiceCreate.init();
});