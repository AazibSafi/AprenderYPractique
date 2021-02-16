/*
*  Stach Operations
*/
var stack = [];
const PUSH='push';
const POP='pop';
const INC='inc';

var operations = ['push 4', 'push 5', 'inc 2 1', 'pop', 'pop'];
superStack(operations);

function superStack(operations) {

    for (i = 0; i < operations.length; i++) {
        var operation = operations[i].split(" ");
	    if(operation[0] == PUSH) {
            push(operation[1]);
        }
        else if(operation[0] == POP) {
            pop();
        }
        else if(operation[0] === INC) {
            inc(operation[1],operation[2]);
        }
        else {
            console.log('Invalid Operation');
        }
    }

}

function push(element) {
    stack.push(element);
    printTopElement();
}

function pop() {
    if(isStackEmpty()) {
        console.log('EMPTY');
    }
    else {
        stack.pop();
        printTopElement();
    }
}

function pop() {
    if(!isStackEmpty()) {
        stack.pop();
    }
    printTopElement();
}

function inc(i,v) {
    for (ind = 0; ind < parseInt(i); ind++) {
        stack[ind] = parseInt(stack[ind]) + parseInt(v);
    }
    printTopElement();
}

function isStackEmpty() {
    return stack.length == 0;
}

function printTopElement() {
    var topElement = isStackEmpty() ? 'EMPTY' : stack[stack.length-1];
    console.log(topElement);
}
