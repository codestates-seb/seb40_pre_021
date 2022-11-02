import { useState } from 'react';
import { TagsInput } from 'react-tag-input-component';
import styled from 'styled-components';
const InputFormStyle = styled.div`
	input {
		padding: 0 0.5rem 0 0.5rem;
		width: 100%;
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
	.rti--container {
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
	.rti--tag {
		padding: 0.25rem 0.2rem 0.25rem 0.2rem;
		border-radius: 4px;
		font-weight: 400;
		color: hsl(206, 100%, 40%);
	}
	.rti--tag button {
		padding: 0 0.2rem;
		margin-left: 0.2rem;
		color: hsl(206, 100%, 40%);
		font-weight: 700;
		border-radius: 3px;
	}
	.rti--tag button:hover {
		background-color: hsl(206, 100%, 40%);
		color: var(--rti-tag);
	}
	.rti--container:focus-within {
		outline: none;
		border-color: #9ecaed;
		box-shadow: 0 0 10px #9ecaed;
	}
`; // Common/InputForm.jsx

const TagForm = ({ separators }) => {
	const [selected, setSelected] = useState([]);
	console.log(selected);
	return (
		<InputFormStyle>
			<div>
				<TagsInput
					value={selected}
					onChange={setSelected}
					name="tags"
					placeHolder="e.g. (c# angularjs sql)"
					separators={separators}
				/>
			</div>
		</InputFormStyle>
	);
};

export default TagForm;
