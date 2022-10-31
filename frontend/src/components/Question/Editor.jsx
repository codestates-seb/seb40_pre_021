import { useState } from 'react';
import styled from 'styled-components';
import parsedHTML from '../../utils/parsedHTML';
import textCharacterCheck from '../../utils/textCharacterCheck';
const Container = styled.div``;
const Header = styled.div``;
const Textarea = styled.textarea`
	font-size: 0.85rem;
	border: 1px solid rgb(179, 183, 188);
	padding: 0.5rem;
	height: 14rem;
	width: 99%;
	border-radius: 3px;
	background-color: white;
	display: flex;
	align-items: center;
	justify-content: center;

	&:focus-within {
		outline: none;
		border-color: #9ecaed;
		box-shadow: 0 0 10px #9ecaed;
	}
	&:focus {
		outline: none;
	}
`;
const Result = styled.div`
	margin-top: 1rem;
	font-size: 1rem;
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
		margin-bottom: 1rem;
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
