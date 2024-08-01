document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/clients')
        .then(response => response.json())
        .then(data => {
            if (data.message === "success") {
                const tbody = document.querySelector('#clientTable tbody');
                data.data.forEach(client => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${client.id}</td>
                        <td>${client.name}</td>
                        <td>${client.email}</td>
                        <td>${client.phone}</td>
                        <td>${client.address}</td>
                    `;
                    tbody.appendChild(row);
                });
            }
        });
});
