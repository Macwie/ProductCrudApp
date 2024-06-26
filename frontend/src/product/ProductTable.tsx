import { Button } from "primereact/button";
import { Column } from "primereact/column";
import { DataTable } from "primereact/datatable";
import { useState } from "react";
import { ApiError } from "../error";
import ProductAddDialog from "./ProductAddDialog";
import "./ProductTable.css";
import { Product } from "./product";

interface ProductTableProps {
  products: Product[];
  onDelete: (productId: number) => void;
  onAdd: (product: Product) => void;
  errors?: ApiError;
  clearErrors: () => void;
}

function ProductTable({ products, onDelete, onAdd, errors, clearErrors }: ProductTableProps) {
  const [isAddDialogVisible, setIsAddDialogVisible] = useState(false);

  const deleteBtnTemplate = (rowData: Product) => {
    return <Button icon="pi pi-trash" severity="danger" onClick={() => onDelete(rowData.id)} />;
  };

  const header = (
    <div className="table-header">
      <h2>Products</h2>
      <Button icon="pi pi-add" label="Add product" onClick={() => setIsAddDialogVisible(true)}></Button>
    </div>
  );

  const formatCurrency = (value: number) => {
    return value + " PLN";
  };

  const priceBodyTemplate = (rowData: Product) => {
    return formatCurrency(rowData.price);
  };

  return (
    <>
      <ProductAddDialog
        visible={isAddDialogVisible}
        visibilityHandler={setIsAddDialogVisible}
        addProductHandler={onAdd}
        errors={errors}
        clearErrors={clearErrors}
      />
      <div className="card">
        <DataTable value={products} header={header} tableStyle={{ minWidth: "50rem" }}>
          <Column field="id" header="Id"></Column>
          <Column field="name" header="Name"></Column>
          <Column header="Price" body={priceBodyTemplate}></Column>
          <Column header="Actions" body={deleteBtnTemplate}></Column>
        </DataTable>
      </div>
    </>
  );
}

export default ProductTable;
