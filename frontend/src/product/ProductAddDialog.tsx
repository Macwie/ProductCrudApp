import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import { FloatLabel } from "primereact/floatlabel";
import { InputNumber } from "primereact/inputnumber";
import { InputText } from "primereact/inputtext";
import { Message } from "primereact/message";
import { useEffect, useState } from "react";
import "./ProductAddDialog.css";
import { ApiError, FieldError } from "../error";
import { Product } from "./product";

interface ProductAddDialogInterface {
  visible: boolean;
  visibilityHandler: (visible: boolean) => void;
  addProductHandler: (product: Product) => void;
  errors?: ApiError;
  clearErrors: () => void;
}

const ErrorMessage = ({ errors, field, clearErrors }: { errors?: ApiError; field: string; clearErrors: () => void }) => {
  if (!errors) {
    return null;
  }

  let fieldErrors: FieldError[] | undefined = errors.fieldErrors?.filter((fieldError: FieldError) => fieldError.field === field);

  if (fieldErrors) {
    return (
      <>
        {fieldErrors.map((fieldError, index) => (
          <Message key={index} severity="error" text={fieldError.field + " " + fieldError.error} />
        ))}
      </>
    );
  }

  useEffect(() => {
    clearErrors(); 
  }, [errors]);
};

function ProductAddDialog({ visible, visibilityHandler, addProductHandler, errors, clearErrors }: ProductAddDialogInterface) {
  const [name, setName] = useState<string | undefined>("");
  const [price, setPrice] = useState<number | null>();

  const handleOnHide = () => {
    setName("");
    setPrice(null);
    visibilityHandler(false);
    clearErrors();
  };

  const handleOnAdd = () => {
    addProductHandler({ name, price } as Product);
  };

  const headerContent = <h3 className="dialog-header">Add new product</h3>;

  const footerContent = (
    <div>
      <Button label="Add" type="submit" autoFocus onClick={() => handleOnAdd()}></Button>
      <Button label="Close" onClick={() => handleOnHide()} />
    </div>
  );

  return (
    <Dialog
      visible={visible}
      modal
      draggable={false}
      header={headerContent}
      footer={footerContent}
      style={{ width: "50rem" }}
      onHide={() => handleOnHide()}
    >
      <form className="form-fields">
        <FloatLabel>
          <InputText id="name" value={name} onChange={(e) => setName(e.target.value)} required={true} />
          <label htmlFor="name">Name</label>
          <ErrorMessage errors={errors} field={"name"} clearErrors={clearErrors} />
        </FloatLabel>
        <FloatLabel>
          <InputNumber id="price" value={price} onValueChange={(e) => setPrice(e.value)} mode="currency" currency="PLN" maxLength={17}/>
          <label htmlFor="price">Price</label>
          <ErrorMessage errors={errors} field={"price"} clearErrors={clearErrors} />
        </FloatLabel>
      </form>
    </Dialog>
  );
}

export default ProductAddDialog;
