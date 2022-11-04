import { useEffect, useState } from 'react';
import styled from 'styled-components';

const TagForm = ({ callback }) => {
	const [tags, setTags] = useState([]);
	const [value, setValue] = useState('');

	function handleKeyDown(e) {
		e.stopPropagation();
		if (e.keyCode === 8 && value === '' && tags.length > 0) {
			const editTag = tags.pop();
			const newTags = [...tags];
			setTags(newTags);
			setValue(editTag + ' ');
		}
		if (e.key !== 'Enter' && e.key !== ' ') return;
		else {
			setValue(value.trim().toLowerCase());
		}
		if (!value.trim()) return;
		setTags([...tags, value]);
		setValue('');
	}

	function removeTag(index) {
		setTags(tags.filter((el, idx) => idx !== index));
	}

	function handleChangeValue(e) {
		let value = e.target.value;
		value = value.replace(/[^a-zA-z-_0-9]/g, '');
		setValue(value);
	}

	useEffect(() => {
		callback(tags);
	}, [tags]);

	return (
		<Style>
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
					value={value}
					pattern="[a-zA-Z0-9]"
					onKeyDown={handleKeyDown}
					onChange={handleChangeValue}
				/>
			</div>
		</Style>
	);
};

const Style = styled.div`
	.container {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		input {
			padding: 0 0.5rem 0 0.5rem;
			border: none;
			font-size: 12px;
			font-weight: 500;
			height: 1.6rem;
			width: 50%;
			&:focus {
				outline: none;
			}
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
		background-color: rgb(227, 236, 243);
		padding: 0.35rem 0.25rem 0.35rem 0.25rem;
		border-radius: 4px;
		font-weight: 400;
		color: hsl(206, 100%, 40%);
		margin-right: 0.25rem;
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
		color: rgb(227, 236, 243);
	}
	.container:focus-within {
		outline: none;
		border-color: #9ecaed;
		box-shadow: 0 0 10px #9ecaed;
	}
`;

export default TagForm;
