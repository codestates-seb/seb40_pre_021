import { useEffect, useState } from 'react';
import styled from 'styled-components';
import markdownParse from '../../utils/markdownParse';
const Container = styled.div`
	width: 100%;
`;
const Header = styled.div``;
const Textarea = styled.textarea`
	font-size: 0.8rem;
	font-family: IBM Plex Mono, monospace;
	border: 1px solid rgb(179, 183, 188);
	padding: 0.75rem;
	height: 14rem;
	width: calc(100% - 1.5rem);
	border-radius: 3px;
	background-color: white;
	display: flex;
	align-items: center;
	justify-content: center;
	resize: vertical;

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
	line-height: 130%;
	word-break: break-all;
	white-space: pre-wrap;
	ul {
		list-style-type: disc;
		margin-left: 1rem;
	}
	ol {
		list-style-type: decimal;
		margin-left: 1rem;
	}

	h1 {
		font-weight: 700;
		font-size: 1.4rem;
	}
	h2 {
		font-weight: 700;
		font-size: 1.25rem;
	}
	h3 {
		font-weight: 700;
	}

	strong {
		font-weight: 700;
	}
	em {
		font-style: italic;
	}
	hr {
		height: 1px;
	}
	pre {
		font-size: 0.8rem;
		background-color: #f6f6f6;
		padding: 0.5rem;
		overflow-x: auto;
	}
	code {
		font-size: 0.8rem;
		background-color: #f6f6f6;
		padding: 0.1rem 0.3rem 0.1rem 0.3rem;
	}

	p,
	section {
		margin-bottom: 1rem;
	}
`;

const Editor = ({ id, callback }) => {
	const [mdText, setMdText] = useState('');

	const handleChange = (e) => {
		setMdText(markdownParse(e.target.value));
	};
	useEffect(() => {
		callback(mdText);
	}, [mdText]);
	return (
		<>
			<Container>
				<Header />
				<Textarea onKeyUp={handleChange} id={id}></Textarea>
				<Result
					mdText={mdText}
					dangerouslySetInnerHTML={{ __html: mdText }}></Result>
			</Container>
		</>
	); // XSS공격 대비 필요. 마크다운 텍스트 에디터를 웹으로 만드는 것의 한계인 듯하다.
	// https://ui.toast.com/weekly-pick/ko_monthly_202006 참고. 여기서도 수동으로 XSS의 가능성이 있는 코드들을 분석해서 따로 파싱한다.
};

export default Editor;
