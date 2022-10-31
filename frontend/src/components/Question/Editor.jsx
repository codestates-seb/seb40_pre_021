import { useState } from 'react';
import styled from 'styled-components';
import parsedHTML from '../../utils/parsedHTML';
import textCharacterCheck from '../../utils/textCharacterCheck';
const Container = styled.div``;
const Header = styled.div``;
const Textarea = styled.textarea``;
const Result = styled.div`
	ul {
	}
	ol {
	}

	h1 {
		font-weight: 700;
	}
	h2 {
	}
	h3 {
	}

	strong {
		font-weight: 700;
	}
	em {
	}
	hr {
	}

	pre {
	}
	code {
	}

	p {
	}
`;
const Editor = () => {
	const [mdText, setMdText] = useState('');
	const handleChange = (e) => {
		setMdText(parsedHTML(e.target.value));
	};

	return (
		<>
			<Container>
				<Header />
				<Textarea onChange={handleChange}></Textarea>
				<Result
					mdText={mdText}
					dangerouslySetInnerHTML={{ __html: mdText }}></Result>
			</Container>
		</>
	); // XSS공격 대비 필요. 마크다운 텍스트 에디터를 웹으로 만드는 것의 한계인 듯하다.
	// https://ui.toast.com/weekly-pick/ko_monthly_202006 참고. 여기서도 수동으로 XSS의 가능성이 있는 코드들을 분석해서 따로 파싱한다.
};

export default Editor;
