import { useState } from 'react';
import styled from 'styled-components';
const InputFormStyle = styled.div`
	input {
		padding: 0 0.5rem 0 0.5rem;
		border: none;
		font-size: 13px;

		&:focus {
			outline: none;
		}
	}

	.blueText {
		color: hsl(206, 100%, 40%);
		font-size: 12px;
	}
	.container {
		input {
			font-size: 12px;
			font-weight: 500;
			height: 1.6rem;
		}
		input::placeholder {
			color: #bbbbbb;
		}
		font-size: 12px;
		border: 1px solid rgb(179, 183, 188);
		border-radius: 2px;
		padding: 0.3rem;
	}
	.tag {
		display: inline;
		padding: 0.25rem 0.2rem 0.25rem 0.2rem;
		border-radius: 4px;
		font-weight: 400;
		color: hsl(206, 100%, 40%);
	}
	.tag button {
		padding: 0 0.2rem;
		margin-left: 0.2rem;
		color: hsl(206, 100%, 40%);
		font-weight: 700;
		border-radius: 3px;
	}
	.tag button:hover {
		background-color: hsl(206, 100%, 40%);
		color: var(--rti-tag);
	}
	.container:focus-within {
		outline: none;
		border-color: #9ecaed;
		box-shadow: 0 0 10px #9ecaed;
	}
`;

const TagForm = () => {
	const [tags, setTags] = useState([]);
	function handleKeyDown(e) {
		if (e.key !== 'Enter') return;
		const value = e.target.value;
		if (!value.trim()) return;
		setTags([...tags, value]);
		e.target.value = '';
	}
	function removeTag(i) {
		setTags(tags.filter((el, idx) => idx !== i));
	}
	return (
		<InputFormStyle>
			<div className="container">
				{tags.map((tag, idx) => (
					<div className="tag" key={idx}>
						<span>{tag}</span>
						<button onClick={() => removeTag(idx)}>&times;</button>
					</div>
				))}
				<input
					type="text"
					placeholder="e.g. (objective-c json windows)"
					onKeyDown={handleKeyDown}></input>
			</div>
		</InputFormStyle>
	);
};
// <InputFormStyle>
// 	<div>
// 		<TagsInput
// 			value={selected}
// 			onKeyUp={setSelected}
// 			name="tags"
// 			placeHolder="e.g. (c# angularjs sql)"
// 			separators={separators}
// 		/>
// 	</div>
// </InputFormStyle>

export default TagForm;
