import { useEffect, useState } from 'react';
import styled from 'styled-components';

const TagForm = ({ callback }) => {
	const [tags, setTags] = useState([]);

	function handleKeyDown(e) {
		let value;
		e.stopPropagation();
		if (e.key !== 'Enter' && e.key !== ' ') return;
		else {
			value = e.target.value.trim().toLowerCase();
			e.target.value = '';
		}
		if (!value.trim()) return;
		setTags([...tags, value]);
	}

	function removeTag(index) {
		setTags(tags.filter((el, idx) => idx !== index));
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
					onKeyDown={handleKeyDown}></input>
			</div>
		</Style>
	);
};

const Style = styled.div`
	.container {
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
