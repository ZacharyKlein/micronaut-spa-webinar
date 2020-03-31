<template>
    <div>
        <form>
            <label for="tenant">Select Tenant:</label>
            <select id="tenant" v-model="tenant">
                <option value="">Select...</option>
                <option value="ACME">ACME</option>
                <option value="Makers">Makers</option>
            </select>
        </form>

        <hr/>

        <table style="width: 800px;">
            <tr>
                <th>Name</th>
                <th>Company</th>
            </tr>
            <tr v-for="product in products" :key="product.name">
                <td>{{product.name}}</td>
                <td>{{product.company}}</td>
            </tr>
        </table>


    </div>
</template>
<script>
    export default {
        name: "ProductList",
        data() {
            return {
                tenant: null,
                products: []
            }
        },
        watch: {
            tenant(val) {
                this.products = [];

                fetch("http://localhost:8080/products/", {
                    method: "GET",
                    headers: {
                        Accept: "application/json",
                        'tenantId': val,
                        Authorization: `Bearer ${localStorage.getItem("access_token")}`
                    }
                }).then(response => response.json())
                .then(json => this.products = json)
                .catch(error => console.warn(error));
            }
        }

    }
</script>