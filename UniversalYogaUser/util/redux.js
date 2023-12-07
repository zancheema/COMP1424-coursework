import { createContext } from "react";

export const classesReducer = (classes, action) => {
    console.log('classesReducer: ' + JSON.stringify(action));
    switch (action.type) {
        case 'replace': {
            return action.data;
        }
        case 'book': {
            for (var c of classes) {
                if (c.instanceId === action.instanceId) {
                    console.log('book: ' + c.instanceId);
                    c.booked = true;
                }
            }
            return classes;
        }
        default: {
            throw Error('Unknown action: ' + action.type);
        }
    }
}

export const ClassesContext = createContext(null);
export const DispatchContext = createContext(null);
