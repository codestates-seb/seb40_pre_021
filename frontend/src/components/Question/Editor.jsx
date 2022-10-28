import { useState } from 'react';
import styled from 'styled-components';
import parsedHTML from '../../utils/parsedHTML';
import textCharacterCheck from '../../utils/textCharacterCheck';
const Container = styled.div``;
const Header = styled.div``;
const Textarea = styled.textarea``;

const Editor = () => {
	const [text, setText] = useState('');
	const handleChange = (e) => {
		setText(e.target.value);
	};
	return (
		<>
			<Container>
				<Header />
				<Textarea></Textarea>
			</Container>
		</>
	);
};

export default Editor;
