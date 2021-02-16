/*
* Count Palindromic substrings
*/

function countPalindromes(s) {
    var subPalindromes = [];

    for(var i = 0; i < s.length; i++) {
        findSubstrings(s,i,i,subPalindromes);
        findSubstrings(s,i,i+1,subPalindromes);
    }

    return subPalindromes.length;
}

function findSubstrings(s, low, high, subPalindromes) {
    while(low>=0 && high<s.length && s[low] == s[high]) {
        subPalindromes.push(s.substring(low, high-low+1));
        low--;
        high++;
    }
}
