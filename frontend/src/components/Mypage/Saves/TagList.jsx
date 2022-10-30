import styled from 'styled-components';

const TagList = ({ tag }) => {
	return (
		<>
			{tag.map((item) => {
				return (
					<Container key={item}>
						<a href="123">{item}</a>
					</Container>
				);
			})}
		</>
	);
};

export default TagList;

const Container = styled.li`
	display: inline;
	margin: 4px 4px 4px 0;

	a {
		display: inline-block;
		font-size: 12px;
		color: #39739d;
		background-color: #e1ecf4;
		padding: 0.4rem 0.5rem;
		margin: 2px 2px 2px 0;
		line-height: 1;
		white-space: nowrap;
		text-decoration: none;
		text-align: center;
		border: 1px solid transparent;
		border-radius: 3px;
		font-weight: 600;
		:hover {
			background-color: #d1e3f0;
			color: #2c5877;
		}
	}
`;
