export interface ApiError {
    status: string;
    message?: string
    fieldErrors?: FieldError[];
}

export interface FieldError {
    field: string;
    error: string;
}