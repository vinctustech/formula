export class Formulae {
    constructor(decls: string)
    formula(name: string): any
    func(name: string, ...args: any[]): any
    get(name: string): any
    set(name: string, value: () => any): void
}
