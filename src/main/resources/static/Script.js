document.getElementById("orderForm").addEventListener("submit", function (e)
{
  e.preventDefault();

  const orderData = 
  {
    farmerName: document.getElementById("farmerName").value,
    fertilizerType: document.getElementById("fertilizerType").value,
    quantity: document.getElementById("quantity").value,
    price: document.getElementById("price").value
  };

  fetch("http://localhost:9096/api/orders",
  {
    method: "POST",
    headers: 
  {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(orderData)
  })
    .then(response => response.json())
    .then(data =>
      {
      alert("Order placed successfully!");
      document.getElementById("orderForm").reset();
      fetchOrders(); // Refresh table
    })
    .catch(error => 
      {
      console.error("Error placing order:", error);
    });
});
function fetchOrders() 
{
  fetch("http://localhost:9096/api/orders")
    .then(response => response.json())
    .then(data => {
      const tableBody = document.getElementById("orderTableBody");
      tableBody.innerHTML = "";

      data.forEach(order => {
        const row = document.createElement("tr");

        row.innerHTML = `
          <td>${order.farmerName}</td>
          <td>${order.fertilizerType}</td>
          <td>${order.quantity}</td>
          <td>${order.price ?? 'N/A'}</td>
          <td>
            ${order.status ?? "Pending"}
            ${
              !order.status || order.status === "Pending"
                ? `<br/>
                   <button onclick="updateStatus(${order.id}, 'Approved')">Approve</button>
                   <button onclick="updateStatus(${order.id}, 'Rejected')">Reject</button>`
                : ""
            }
          </td>
        `;

        tableBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error("Error fetching orders:", error);
    });
}
function updateStatus(id, newStatus)
  {
  fetch(`http://localhost:9096/api/orders/${id}/status`, 
        {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(newStatus)
  })
    .then(res => res.json())
    .then(data => {
      alert("Status updated to " + newStatus);
      fetchOrders(); // Refresh table
    })
    .catch(error =>
      {
      console.error("Error updating status:", error);
    });
}
window.onload = fetchOrders;
