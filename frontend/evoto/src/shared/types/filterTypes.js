/**
 * @typedef {Object} FieldConfig
 * @property {string} name
 * @property {string} label
 * @property {"text" | "select"} type
 * @property {any[]} [options]
 * @property {string} [fetchOptionsOn]
 * @property {(parentId: any) => Promise<any[]>} [fetchOptions]
 * @property {{ pattern?: RegExp, title?: string }} [validation]
 */
