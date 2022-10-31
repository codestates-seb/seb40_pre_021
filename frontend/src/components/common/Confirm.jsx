import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css
import styled from 'styled-components';

const ConfirmStyle = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	h1 {
		display: block;
		font-size: 2em;
		margin-top: 0.67em;
		margin-bottom: 0.67em;
		margin-left: 0;
		margin-right: 0;
		font-weight: bold;
	}
	p {
		margin-top: 0.67em;
		margin-bottom: 0.67em;
	}
	div {
		width: 100%;
		display: flex;
		justify-content: space-around;
	}
	button {
		border: 1px solid rgb(179, 183, 188);
		font-size: large;
	}
`;
export default function Confirm({ onClose, title, content, callback }) {
	return (
		//class='custom-ui' <<react-confirm-alert 에서 모달창 스타일을 만들어줌
		<ConfirmStyle className="custom-ui">
			<h1>{title}</h1>
			<p>{content}</p>
			<div>
				<button
					onClick={() => {
						callback();
						onClose();
					}}>
					Yes!
				</button>
				<button onClick={onClose}>No~</button>
			</div>
		</ConfirmStyle>
	);
}
