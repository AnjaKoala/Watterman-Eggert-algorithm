#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

const int LINE_WIDTH = 80;

int main() {
	string first;
	getline(cin, first);
	string second;
	getline(cin, second);

	int length = max(first.length(), second.length());

	for (int i = 0; i < length; i += LINE_WIDTH) {
		cout << (i + 1) << ":\n";
		int n1 = min((int)first.length(), i + LINE_WIDTH);
		cout << " ";
		for (int j = i; j < n1; j++) {
			cout << first[j];
		}
		int n2 = min((int)second.length(), i + LINE_WIDTH);
		cout << "\n";
		cout << " ";
		for (int j = i; j < n2; j++) {
			cout << second[j];
		}
		cout << "\n";
	}

	return 0;
}
