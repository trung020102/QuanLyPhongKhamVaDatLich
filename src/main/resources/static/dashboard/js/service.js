const MedicalServiceList = (function () {
    const module = {
        findAllServiceUrl: '/api/medical-service',
        dataTableSelector: $("#medical-service-table"),

    };

    module.init = () => {
        renderMedicalServiceList()
    }

    const renderMedicalServiceList = () => {
        const serviceListDatatable = module.dataTableSelector.DataTable({
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
                        `<button class="open-edit-user-modal-btn btn btn-sm btn-outline-primary border-0">
                            <i class="fa fa-edit"></i>
                        </button>`
                },
            ],
        });

        /* Create increment number for ordinal column */
        serviceListDatatable.on('draw.dt', function () {
            const PageInfo = serviceListDatatable.page.info();
            serviceListDatatable.column(0, {page: 'current'}).nodes().each(function (cell, i) {
                cell.innerHTML = i + 1 + PageInfo.start;
            });
        });
    }
    return module;
})();

const MedicalServiceCreate = (function() {
    const module = {
        modalCreateService: $('#serviceCreateModal'),
        serviceNameSelector: $('#service_name'),
        servicePriceSelector: $('#service_price'),
        serviceDescriptionSelector: $('#service_description'),
        createBtnSelector: $('#okServiceBtn')
    }
    return module;
})();


$(function () {
    MedicalServiceList.init();
});