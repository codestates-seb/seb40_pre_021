import { useState } from 'react';
import styled from 'styled-components';
import parsedHTML from '../../utils/parsedHTML';
import textCharacterCheck from '../../utils/textCharacterCheck';
const Container = styled.div``;
const Header = styled.div``;
const Textarea = styled.textarea``;
const Result = styled.div``;
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
				<Result mdText={mdText}>{mdText}</Result>
			</Container>
		</>
	);
};

export default Editor;
