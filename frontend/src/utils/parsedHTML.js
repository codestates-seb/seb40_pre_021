const parsedHTML = (str) => {
	const UlStart = /^\s*\n-\s/gm; // 엔터를 입력하고, - 문자열 후 엔터를 다시 한 번 입력하면 리스트 시작
	const UlEnd = /^(-.+)\s*\n([^-])/gm; //리스트 클로징
	const UlLi = /^-(.+)\s/gm; //리스트의 각 항목
	const OlStart = /^\d\.(.*?)\n/gm; // 숫자를 입력하고, '.'을 입력한 뒤 스페이스 바를 누르면 리스트 시작
	const OlEnd = /^(\d\..+)\s*\n([^\d.])/gm; //리스트 클로징
	const OlLi = /^\d\.\s(.+)/gm; //리스트의 각 항목

	const H3 = /[#]{3}\s(.+)/g; // ### text
	const H2 = /[#]{2}\s(.+)/g; /// ## text
	const H1 = /[#]{1}\s(.+)/g; // # text

	const Strong = /[*_]{2}(.+)[*_]{2}/gm; // **text** 혹은 __text__ (굵게)
	const Em = /[*_]{1}(.+)[*_]{1}/gm; // *text* _text_(이탤릭)
	//const Hr = /\n[*\-_]{3}\n/gm; // 엔터 사이에 ***, ---, ___를 입력하면 수평선을 그어줌

	const Pre = /[`]{3}\s(.+)\s[`]{3}/gm; //```로 텍스트를 감싸면 pre
	const Code = /[`]{1}(.+)[`]{1}/gm; // `로 텍스트를 감싸면 인라인 코드

	const Pgh = /\n\n/gm; // 엔터 두 번 치면 문단이 분리됨

	let text = '<article>' + String(str) + '</article>';

	text = text.replace(UlStart, '<ul>\n-');
	text = text.replace(UlEnd, '$1\n</ul>\n\n$2');
	text = text.replace(UlLi, '<li>$1</li>');
	text = text.replace(OlStart, '<ol>\n1.');
	text = text.replace(OlEnd, '$1\n</ol>\n\n$2');
	text = text.replace(OlLi, '<li>$1</li>');

	text = text.replace(H3, '<h3>$1</h3>');
	text = text.replace(H2, '<h2>$1</h2>');
	text = text.replace(H1, '<h1>$1</h1>'); //제목을 파싱합니다.

	text = text.replace(Strong, '<strong>$1</strong>');
	text = text.replace(Em, '<em>$1</em>');
	//text = text.replace(Hr, '<hr />');
	text = text.replace(Pre, '<pre>$1</pre>');
	text = text.replace(Code, '<code>$1</code>');

	text = text.replace(Pgh, '</p><p>');

	return text;
}; //실제로 상세페이지에서 보일 HTML을 리턴합니다.

export default parsedHTML;
